package com.br.crepaldi.imobiliaria.expositor.imovel.cadastrar;

import com.br.crepaldi.imobiliaria.expositor.imovel.Imovel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class CadastrarImoveisService {

    private final CadastrarImoveisRepository repository;

    // Construtor para injeção de dependência
    public CadastrarImoveisService(CadastrarImoveisRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<ImovelDtoCadastroParam> cadastrar(ImovelDtoCadastroParam dto) throws IOException {

        MultipartFile imagem = dto.imagem();
        String fileName = imagem.getOriginalFilename();
        Path uploadPath = Paths.get("Assets/imgs/imoveis");

        // Verifica e cria diretório se não existir
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Cria o caminho completo do arquivo
        Path filePath = uploadPath.resolve(fileName);

        // Remove o arquivo existente, se houver
        if (Files.exists(filePath)) {
            Files.delete(filePath);
        }

        // Salva o arquivo
        try (var inputStream = imagem.getInputStream()) {
            Files.copy(inputStream, filePath);
        }

        // Cria e salva o imóvel
        Imovel imovel = ImovelDtoCadastroParam.toImovel(dto, fileName);
        repository.save(imovel);

        return ResponseEntity.ok(dto);
    }
}
