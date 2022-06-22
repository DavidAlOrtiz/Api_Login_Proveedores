package mx.edu.app.uno.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import mx.edu.app.uno.web.domain.Usuario;
import mx.edu.app.uno.web.firebase.FirebaseConfirg;

@Service
public class UsuarioService {

    @Autowired
    private FirebaseConfirg firebaseConfirg;

    public static final String NOMBRE_COLECION = "usuarios";

    public String guardarCliente(Usuario usuario) {
        CollectionReference user = getCollection();
        ApiFuture<WriteResult> collectionApi = user.document(usuario.getId()).create(usuario);
        return "vientos";
    }

    public List<Usuario> getUsuario() {

        ApiFuture<QuerySnapshot> query = getCollection().get();
        List<Usuario> usuariosList = new ArrayList<>();
        Usuario user;
        try {
            for (DocumentSnapshot usr : query.get().getDocuments()) {
                user = usr.toObject(Usuario.class);
                user.setId(usr.getId());
                usuariosList.add(user);
            }
        } catch (InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return usuariosList;
    }

    public String actualizarCliente(String id, Usuario usuario) {
        CollectionReference user = getCollection();
        usuario.setId(id.trim());
        ApiFuture<WriteResult> collectionApi = user.document(id).set(usuario);
        return "vientos Actualizado";
    }

    public String eliminar(String id) {
        CollectionReference user = getCollection();
        ApiFuture<WriteResult> collectionApi = user.document(id).delete();
        return "Eliminado buey";
    }

    public Usuario obtenerUsuario(String id) throws InterruptedException, ExecutionException {
        CollectionReference user = getCollection();
        ApiFuture<DocumentSnapshot> collectionApi = user.document(id).get();
        DocumentSnapshot docmento = collectionApi.get();
        Usuario usuario = new Usuario();
        if (docmento.exists()) {
            usuario = docmento.toObject(Usuario.class);
        }
        return usuario;
    }

    public Usuario usuarioValido(Usuario usuario) {
        ApiFuture<QuerySnapshot> query = getCollection().get();
        List<Usuario> usuariosList = new ArrayList<>();
        Usuario user = null;
        try {
            for (DocumentSnapshot usr : query.get().getDocuments()) {
                user = usr.toObject(Usuario.class);
                if (usuario.getEmail().toString().equals(user.getEmail().toString())
                        && usuario.getPasword().toString().equals(user.getPasword().toString())) {
                    return user;
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    ////////////////////////////////
    public CollectionReference getCollection() {
        return firebaseConfirg.getFirestore().collection(NOMBRE_COLECION);
    }
}
