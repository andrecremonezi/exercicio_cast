package br.com.cast.turmaformacao.exercicio;

import android.app.Application;

import br.com.cast.turmaformacao.exercicio.util.ApplicationUtil;

/**
 * Created by Administrador on 25/09/2015.
 */
public class ApplicationManager extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationUtil.setApplicationContext(getApplicationContext());

    }
}
