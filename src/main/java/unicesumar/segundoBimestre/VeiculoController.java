package unicesumar.segundoBimestre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService service;

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping
    public List<Veiculo> getAll() {
        return service.getAll();
    }

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/{id}")
    public Veiculo getById(@PathVariable("id") String id) {
        Veiculo resultante = service.getById(id);
        return resultante;
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public String post(@RequestBody Veiculo novoVeiculo) {
        return service.save(novoVeiculo);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") String id) {
        service.deleteById(id);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @PutMapping("/{id}")
    public void put(@PathVariable("id") String id, @RequestBody Veiculo alterado) {
        if (!id.equals(alterado.getId())) {
            throw new RuntimeException("Id do recurso não confere com Id do body!");
        }
        service.save(alterado);
    }
}
