package com.example.appcursobradesco;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class HttpService extends AsyncTask<Void, Void, CEP> {
    private final String cepInserido;

    public HttpService(String cepInserido) {
        this.cepInserido = cepInserido;
    }

    @Override
    protected CEP doInBackground(Void... voids){
        StringBuilder resposta = new StringBuilder();

        if(this.cepInserido != null && this.cepInserido.length() == 8){
            try{
                URL url = new URL("https://viacep.com.br/ws/" + this.cepInserido + "/json/");

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                connection.setDoOutput(true);
                connection.setConnectTimeout(5000);
                connection.connect();

                Scanner scanner = new Scanner(url.openStream());

                while(scanner.hasNext()) {
                    resposta.append(scanner.nextLine());
                }

            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return new Gson().fromJson(resposta.toString(), CEP.class);
    }
}
