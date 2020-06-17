package unicesumar.segundoBimestre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class VeiculoService {
    @Autowired
    private VeiculoRepository repository;

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public Veiculo getById(String id) {
        return repository.findById(id).get();
    }

    public List<Veiculo> getAll() {
        return repository.findAll();
    }

    public String save(Veiculo veiculo) {

        return this.repository.save(veiculo).getId();

    }

}
