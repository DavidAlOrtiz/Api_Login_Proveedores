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
import mx.edu.app.uno.web.firebase.FirebaseConfirg;

@Service
public class EntidaService {

    @Autowired
    private FirebaseConfirg firebaseConfirg;

    public static final String NOMBRE_COLECION = "entidad";

    public List<Entidad> getEntidades() {

        ApiFuture<QuerySnapshot> query = getCollection().get();
        List<Entidad> entidadpioList = new ArrayList<>();
        Entidad entidad;
        try {
            for (DocumentSnapshot usr : query.get().getDocuments()) {
                entidad = usr.toObject(Entidad.class);
                // user.setId(usr.getId());
                entidadpioList.add(entidad);
            }
        } catch (InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return entidadpioList;
    }

    public List<Entidad> getEntidad(String id) {

        ApiFuture<QuerySnapshot> query = getCollection().get();
        List<Entidad> entidadpioList = new ArrayList<>();
        List<Entidad> entidadpioListFiltrada = new ArrayList<>();
        Entidad entidad;
        try {
            for (DocumentSnapshot usr : query.get().getDocuments()) {
                entidad = usr.toObject(Entidad.class);
                // user.setId(usr.getId());
                entidadpioList.add(entidad);
            }
            entidadpioListFiltrada = entidadpioList.stream().filter(x -> x.getId().equals(id))
                    .collect(Collectors.toList());
        } catch (InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return entidadpioListFiltrada;
    }

    ////////////////////////////////
    public CollectionReference getCollection() {
        return firebaseConfirg.getFirestore().collection(NOMBRE_COLECION);
    }
}
