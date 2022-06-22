package mx.edu.app.uno.web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;

import mx.edu.app.uno.web.domain.Proveedor;
import mx.edu.app.uno.web.firebase.FirebaseConfirg;

@Service
public class ProveedorService {

    @Autowired
    private FirebaseConfirg firebaseConfirg;

    public static final String NOMBRE_COLECION = "proveedores";

    // public String guardarCliente(Proveedore usuario) {
    // CollectionReference user = getCollection();
    // ApiFuture<WriteResult> collectionApi =
    // user.document(usuario.getId()).create(usuario);
    // return "vientos";
    // }

    public List<Proveedor> getProvedores() {

        ApiFuture<QuerySnapshot> query = getCollection().get();
        List<Proveedor> provedorList = new ArrayList<>();
        Proveedor provedor;
        try {
            for (DocumentSnapshot usr : query.get().getDocuments()) {
                provedor = usr.toObject(Proveedor.class);
                // provedor.setId(usr.getId());
                provedorList.add(provedor);
            }
        } catch (InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return provedorList;
    }

    public boolean guardarProveedor(Proveedor provedor) {
        CollectionReference user = getCollection();
        ApiFuture<WriteResult> collectionApi = user.document(provedor.getId()).create(provedor);
        boolean respesta = false;
        try {
            if (collectionApi != null) {
                respesta = true;
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace(System.out);
        }
        return respesta;
    }

    public boolean actualizarProveedor(String id, Proveedor proveedor) {
        CollectionReference user = getCollection();
        proveedor.setId(id.trim());
        boolean respuesta = false;
        try {
            ApiFuture<WriteResult> collectionApi = user.document(id).set(proveedor);
            if (collectionApi != null) {
                respuesta = true;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return respuesta;
    }

    public boolean eliminar(String id) {
        CollectionReference user = getCollection();
        boolean respuesta = false;
        try {
            ApiFuture<WriteResult> collectionApi = user.document(id).delete();
            if (collectionApi != null) {
                respuesta = true;
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
        return respuesta;
    }

    ////////////////////////////////
    public CollectionReference getCollection() {
        return firebaseConfirg.getFirestore().collection(NOMBRE_COLECION);
    }
}
