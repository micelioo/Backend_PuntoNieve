package Pasteleria.PuntoNieve.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@Entity
@Table(name = "carrito")
@NoArgsConstructor
@AllArgsConstructor
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCarrito;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false)
    private Integer precioUnitario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(
            name = "id_pedido",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_carrito_pedido")
    )
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(
            name = "id_producto",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_carrito_producto")
    )
    private Producto producto;

    @JsonProperty("nombreProducto")
    public String getNombreProducto() {
        return (producto != null) ? producto.getNombre() : null;
    }

    @JsonProperty("categoriaProducto")
    public String getCategoriaProducto() {
        if (producto == null || producto.getCategoria() == null) return null;
        return producto.getCategoria().getDescripcion();
    }

    @JsonProperty("subtotal")
    public Integer getSubtotal() {
        if (cantidad == null || precioUnitario == null) return null;
        return cantidad * precioUnitario;
    }

    @JsonProperty("estadoPedido")
    public String getEstadoPedido() {
        return (pedido != null) ? pedido.getEstado() : null;
    }
}