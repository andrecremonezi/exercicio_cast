package br.com.cast.turmaformacao.exercicio.controllers.sync;

import java.util.List;

/**
 * Created by Administrador on 28/09/2015.
 */
public interface ProductInterfaceListerner<T> {

    public void refreshList();
    public void updateList(List<T> lista);


}
