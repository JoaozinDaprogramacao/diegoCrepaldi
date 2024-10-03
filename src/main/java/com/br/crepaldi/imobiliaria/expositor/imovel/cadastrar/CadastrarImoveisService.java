package com.br.crepaldi.imobiliaria.expositor.imovel.cadastrar;

import com.br.crepaldi.imobiliaria.expositor.imovel.Imovel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class CadastrarImoveisService {

    private final CadastrarImoveisRepository repository;

    // Diretório externo para armazenamento de imagens no Linux
    private final String uploadDir = System.getProperty("user.home") + "/imoveis/uploads/";

    // Construtor para injeção de dependência
    public CadastrarImoveisService(CadastrarImoveisRepository repository) {
        this.repository = repository;

        // Verifica se o diretório existe, se não, cria o diretório
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            try {
                Files.createDirectories(uploadPath);
                System.out.println("Diretório de upload criado: " + uploadPath);
            } catch (IOException e) {
                throw new RuntimeException("Não foi possível criar o diretório de upload", e);
            }
        }
    }

    public ResponseEntity<ImovelDtoCadastroParam> cadastrar(ImovelDtoCadastroParam dto) throws IOException {

        List<MultipartFile> imagens = dto.imagem();
        List<String> nomesImagens = new ArrayList<>();

        System.out.println("Caminho do upload: " + uploadDir);
        System.out.println("Imagens recebidas: " + imagens.size());

        for (MultipartFile imagem : imagens) {
            String fileName = imagem.getOriginalFilename();

            // Caso o nome do arquivo esteja em branco, usar uma imagem padrão
            if (fileName == null || fileName.isBlank()) {
                fileName = "indisponivel.jpg"; // Nome da imagem padrão
            }

            System.out.println("Processando imagem: " + fileName);
            Path filePath = Paths.get(uploadDir).resolve(fileName);

            // Se o arquivo já existir e não for a imagem padrão, remover o arquivo
            if (!fileName.equals("indisponivel.jpg") && Files.exists(filePath)) {
                System.out.println("Arquivo existente, removendo: " + filePath);
                Files.delete(filePath);
            }

            // Copia a nova imagem para o diretório, exceto se for a imagem padrão
            if (!fileName.equals("indisponivel.jpg")) {
                try (var inputStream = imagem.getInputStream()) {
                    Files.copy(inputStream, filePath);
                    System.out.println("Arquivo salvo em: " + filePath);
                }
            }

            // Adiciona o nome da imagem (ou o nome da imagem padrão)
            nomesImagens.add(fileName);
        }

        System.out.println("Nomes das imagens salvas: " + nomesImagens);

        // Cria e salva o imóvel com a lista de nomes de imagens
        Imovel imovel = ImovelDtoCadastroParam.toImovel(dto, nomesImagens);

        repository.save(imovel);

        return ResponseEntity.ok(dto);
    }

}