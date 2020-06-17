package unicesumar.segundoBimestre;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
@AutoConfigureMockMvc
class TestesComApiDeVeiculos {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private VeiculoController veiculoController;

    @MockBean
    private VeiculoService veiculoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testeGetById() throws Exception {
        Veiculo existente = new Veiculo("1", "Carro", 4, 100);

        when(veiculoService.getById("1")).thenReturn(existente);

        mockMvc.perform(get("/api/veiculos/1"))
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.modelo").value("Carro"))
                .andExpect(jsonPath("$.qtdeDeRodas").value(4))
                .andExpect(jsonPath("$.potenciaVeiculo").value(100))
                .andExpect(status().isOk());
    }

    @Test
    void testeGetAll() throws Exception {


        Veiculo v1 = new Veiculo("1", "Motocicleta", 2, 100);
        Veiculo v2 = new Veiculo("2", "Carro", 4, 200);
        Veiculo v3 = new Veiculo("3", "Caminhao", 6, 300);

        when(veiculoService.getAll()).thenReturn(Arrays.asList(v1, v2, v3));

        mockMvc.perform(get("/api/veiculos"))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$.[0].id").value("1"))
                .andExpect(jsonPath("$.[1].id").value("2"))
                .andExpect(jsonPath("$.[2].id").value("3"))
                .andExpect(jsonPath("$.[0].modelo").value("Motocicleta"))
                .andExpect(jsonPath("$.[1].modelo").value("Carro"))
                .andExpect(jsonPath("$.[2].modelo").value("Caminhao"))
                .andExpect(jsonPath("$.[0].qtdeDeRodas").value("2"))
                .andExpect(jsonPath("$.[1].qtdeDeRodas").value("4"))
                .andExpect(jsonPath("$.[2].qtdeDeRodas").value("6"))
                .andExpect(jsonPath("$.[0].potenciaVeiculo").value("100.0"))
                .andExpect(jsonPath("$.[1].potenciaVeiculo").value("200.0"))
                .andExpect(jsonPath("$.[2].potenciaVeiculo").value("300.0"))
                .andExpect(status().isOk());
    }

    @Test
    void testePost() throws Exception {
        when(veiculoService.save(ArgumentMatchers.any(Veiculo.class))).thenReturn("999");

        Map<String, String> Veiculo = new HashMap<String, String>() {{
            put("id", "999");
            put("modelo", "Lancha");
            put("qtdeDeRodas", "0");
            put("potenciaVeiculo", "150");
        }};

        String veiculoComoJson = objectMapper.writeValueAsString(Veiculo);

        mockMvc.perform(post("/api/veiculos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(veiculoComoJson))
                .andExpect(status().isCreated())
                .andExpect(content().string("999"));
    }

    @Test
    void testeDeleteById() throws Exception {

        Veiculo veiculo = new Veiculo("555", "Carro", 4, 120);
        when(veiculoService.getById(veiculo.getId())).thenReturn(veiculo);
        doNothing().when(veiculoService).deleteById(veiculo.getId());

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/veiculos/{id}", veiculo.getId())).andExpect(status().isOk());

    }

    @Test
    void testePut() throws Exception {

        when(veiculoService.save(ArgumentMatchers.any(Veiculo.class))).thenReturn("555");

        Map<String, String> Veiculo = new HashMap<String, String>() {{
            put("id", "555");
            put("modelo", "Lancha");
            put("qtdeDeRodas", "0");
            put("potenciaVeiculo", "150");
        }};

        String veiculoComoJson = objectMapper.writeValueAsString(Veiculo);

        mockMvc.perform(put("/api/veiculos/555")
                .contentType(MediaType.APPLICATION_JSON)
                .content(veiculoComoJson))
                .andExpect(status().isOk())
                .andExpect(content().string("555"));

    }


}
