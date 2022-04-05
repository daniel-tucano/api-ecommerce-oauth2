package com.example.api.ecommerce.request;

import com.example.api.ecommerce.model.Usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

public class UsuarioRequest implements Serializable {

    @Email
    @NotBlank
    private String email;

    public UsuarioRequest(String email) {
        this.email = email;
    }

    public UsuarioRequest() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Usuario toUsuario() {
        return new Usuario(email);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioRequest entity = (UsuarioRequest) o;
        return Objects.equals(this.email, entity.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "email = " + email + ")";
    }
}
