package Paqueteria;

import java.io.Serializable;

public class DatosOrden implements Serializable {
    private String ordenId;
    private String nombreCliente;
    private String emailCliente;
    private String direccion;

    public DatosOrden(String IdOrden, String NombreCliente, String EmailCliente, String Direccion) {
        this.ordenId = IdOrden;
        this.nombreCliente = NombreCliente;
        this.emailCliente = EmailCliente;
        this.direccion = Direccion;
    }

    public String obtenerIdOrden() {
        return ordenId;
    }

    public String obtenerNombreCliente() {
        return nombreCliente;
    }

    public String obtenerEmailCliente() {
        return emailCliente;
    }

    public String obtenerDireccion() {
        return direccion;
    }
}
