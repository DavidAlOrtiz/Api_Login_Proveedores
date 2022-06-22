package mx.edu.app.uno.web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import mx.edu.app.uno.web.domain.Entidad;
import mx.edu.app.uno.web.domain.Municipio;
import mx.edu.app.uno.web.firebase.FirebaseConfirg;

@Service
public class MunicipioService {

    @Autowired
    private FirebaseConfirg firebaseConfirg;

    public static final String NOMBRE_COLECION = "municipio";

    public List<Municipio> getMunicipios() {

        ApiFuture<QuerySnapshot> query = getCollection().get();
        List<Municipio> municipioList = new ArrayList<>();
        List<Municipio> estadoFiltrado = new ArrayList<>();
        Municipio municipio;
        try {
            for (DocumentSnapshot usr : query.get().getDocuments()) {
                municipio = usr.toObject(Municipio.class);
                // user.setId(usr.getId());
                municipioList.add(municipio);
            }
        } catch (InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return municipioList;
    }

    public List<Municipio> getMunicipio(String id) {

        ApiFuture<QuerySnapshot> query = getCollection().get();
        List<Municipio> municipioList = new ArrayList<>();
        List<Municipio> estadoFiltrado = new ArrayList<>();
        Municipio municipio;
        try {
            for (DocumentSnapshot usr : query.get().getDocuments()) {
                municipio = usr.toObject(Municipio.class);
                // user.setId(usr.getId());
                municipioList.add(municipio);
            }
            estadoFiltrado = municipioList.stream().filter(x -> x.getId().equals(id))
                    .collect(Collectors.toList());
        } catch (InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return estadoFiltrado;
    }

    ////////////////////////////////
    public CollectionReference getCollection() {
        return firebaseConfirg.getFirestore().collection(NOMBRE_COLECION);
    }
}
