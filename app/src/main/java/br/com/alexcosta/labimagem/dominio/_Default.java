package br.com.alexcosta.labimagem.dominio;

/**
 * Created by acostas on 13/07/16.
 */
//Classe para BD online
public class _Default {
    protected String _mensagem;
    protected boolean _status;

    public _Default(){
        this._status = true;
        this._mensagem = "";
    }

    public String getMensagem() {
        return _mensagem;
    }

    public void setMensagem(String mensagem) {
        this._mensagem = mensagem;
    }

    public boolean is_status() {
        return _status;
    }

    public void set_status(boolean _status) {
        this._status = _status;
    }
}
