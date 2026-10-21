package br.com.bompreco.sisvarejo.vendas.interno;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EndpointPrecoTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void calculaPrecoPelaFachada() throws Exception {
        mockMvc.perform(post("/v1/precos/calcular")
                        .contentType("application/json")
                        .content("""
                                {
                                  "produtoId": "PROD-001",
                                  "lojaId": "LOJA-001",
                                  "data": "2026-01-15",
                                  "quantidade": 1,
                                  "fidelidade": false
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.precoFinal").value(9.00));
    }
}
