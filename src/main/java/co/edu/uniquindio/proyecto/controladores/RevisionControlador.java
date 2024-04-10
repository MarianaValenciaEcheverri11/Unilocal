package co.edu.uniquindio.proyecto.controladores;


import co.edu.uniquindio.proyecto.servicios.interfaces.RevisionServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/revision")
public class RevisionControlador {

    RevisionServicio revisionServicio;



}
