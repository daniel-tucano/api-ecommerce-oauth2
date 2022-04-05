package com.example.api.ecommerce.response;

import com.example.api.ecommerce.model.Usuario;

import java.io.Serializable;
import java.util.Objects;

public class UsuarioResponse implements Serializable {
    private final Long id;
    private final String email;

    public  UsuarioResponse(Usuario usuario) {
        this.id = usuario.getId();
        this.email = usuario.getEmail();
    }

    public UsuarioResponse(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioResponse entity = (UsuarioResponse) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.email, entity.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "email = " + email + ")";
    }
}
