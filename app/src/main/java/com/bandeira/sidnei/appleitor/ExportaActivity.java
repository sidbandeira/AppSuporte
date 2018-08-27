package com.bandeira.sidnei.appleitor;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bandeira.sidnei.appleitor.Adaptadores.AdapterListagemExportaColeta;
import com.bandeira.sidnei.appleitor.Classes.Coleta;
import com.bandeira.sidnei.appleitor.Classes.ColetaItem;
import com.bandeira.sidnei.appleitor.Repositorio.ColetaItemRepositorio;
import com.bandeira.sidnei.appleitor.Repositorio.ColetaRepositorio;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ExportaActivity extends AppCompatActivity {
    private ListView listaColeta;
    List<Coleta> coleta = new ArrayList<Coleta>();
    private Integer posicao = 0;
    private Spinner spDelimitador;
    private String delimitador = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exporta);

        spDelimitador = (Spinner) findViewById(R.id.spDelimitador);
        listaColeta = (ListView)findViewById(R.id.listagemColetas);
        listarColetas();

    }

    public void btExportar(View view) {
        delimitador = (String) spDelimitador.getSelectedItem();
        Integer contador = 0;
        String anexo = "";

        if (delimitador.contains("Selecione")) {
            Toast.makeText(this, "Escolha um delimitador", Toast.LENGTH_SHORT).show();
            spDelimitador.hasFocus();
        } else {
            Adapter ad = listaColeta.getAdapter();
            for (int i = 0; i < ad.getCount(); i++) {
                Coleta col = (Coleta) ad.getItem(i);
                if (col.isMarcado()) {
                    montaArquivo((int)col.get_id(),col.getcoletadescricao());
                    contador++;
                    anexo = anexo + col.get_id()+"_"+col.getcoletadescricao() + ".txt;";
                }
            }

            if (contador > 0){

                EnviarEmail(contador, anexo);
                Toast.makeText(this,"Exportação realizada com sucesso!",Toast.LENGTH_SHORT).show();
            }

        }
    }

    public void btCancelar(View view) {
        finish();
    }

    private void listarColetas(){
        ColetaRepositorio rep = new ColetaRepositorio(this);
        coleta = rep.buscarColeta(null);
        listaColeta.setAdapter(new AdapterListagemExportaColeta(this, (ArrayList<Coleta>) coleta ));
    }

    private void montaArquivo(Integer codigoColeta, String nomeColeta){
        try {
            Integer contador = 0;
            List<ColetaItem> colItem = new ArrayList<ColetaItem>();
            ColetaItemRepositorio rep = new ColetaItemRepositorio(this);

            colItem = rep.buscarColeta(codigoColeta.toString());

            StringBuffer texto = new StringBuffer();
            for (int i = 0; i < colItem.size();i++){
                texto.append( colItem.get(i).getCodbarras() + delimitador + colItem.get(i).getQuantidade() + "\n");
                contador ++;
            }

            if (contador > 0){
                salvarExterno(texto.toString() ,codigoColeta+"_"+nomeColeta + ".txt");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ####### MANUPULACAO DO ARQUIVO TEXTO #########
    //Função: salvarExterno(Text) - Irá salvar o texto na memória Externa
    public String salvarExterno(String dados, String nomeArquivo) throws IOException {
        //Tratativa para memória externa
        String status = Environment.getExternalStorageState();

        //Verifica se está montado o SD Card
        if( !status.equals(Environment.MEDIA_MOUNTED)){
            throw new IOException("O SD Card não montado ou não disponível!!!");
        }
        //Em caso de montado, irá pegar o diretorio padrão
        File dir = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        File  dir2 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(dir2, nomeArquivo); //Criar arquivo ou reutilizar
        file.getParentFile().mkdirs();
        PrintWriter pw = new PrintWriter(file);	//Funcao para escrita

        try{
            pw.print(dados);
            return file.getPath();
        }finally {
            pw.close();
        }
    }

    // FUNCAO RESPONSAVEL PELO ENVIO DE EMAIL
    public void EnviarEmail(Integer NumeroAnexos, String Anexo) {
        Intent intent = new Intent(Intent.ACTION_SEND_MULTIPLE);
        intent.setType("text/plain");
        intent.putExtra(intent.EXTRA_EMAIL, new String[]{""});
        //intent.putExtra(Intent.EXTRA_SUBJECT, "Pedido de Compras");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Arquivos Coletados");
        //intent.putExtra(Intent.EXTRA_TEXT, "Em anexo pedido de compras.");
        intent.putExtra(Intent.EXTRA_TEXT, "Segue em anexo arquivo(s) coletado(s).");

        //SPLIT PARA CARREGAR OS ANEXOS
        String CurrentString = Anexo;
        String[] anexos = CurrentString.split(";");

        File baseDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        baseDir.mkdirs();
        //File f1 = new File(baseDir, Anexo);
        ArrayList<Uri> uris = new ArrayList<Uri>();

        for (Integer i = 0; i < NumeroAnexos ;i++){
            File f1 = new File(baseDir, anexos[i]);
            Uri uri = FileProvider.getUriForFile(getApplicationContext(),"com.bandeira.sidnei.appleitor.provider",f1);
            uris.add(uri);
        }

        intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
        startActivity(intent);
    }
}
