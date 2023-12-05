package com.liferay.CommunityApp.controllers;

import com.liferay.CommunityApp.dtos.CommunityDTO;
import com.liferay.CommunityApp.models.CommunityModel;
import com.liferay.CommunityApp.service.CommunityService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("communities/api/")
public class CommunityController {

    @Autowired
    CommunityService communityService;

    @Operation(summary = "Obter todas as comunidades", description = "Endpoint para obter uma lista de todas as comunidades cadastradas.")
    @GetMapping("getAll")
    public ResponseEntity<List<CommunityModel>> findAll() {
        List<CommunityModel> communities = communityService.findAll();
        return ResponseEntity.ok().body(communities);
    }

    @Operation(summary = "Obter uma comunidade por ID", description = "Endpoint para obter uma comunidade específica por seu ID.")
    @GetMapping("getOne/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") UUID id) {
        Optional<CommunityModel> community = Optional.ofNullable(communityService.findById(id));
        if (community.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Community not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(community.get());
    }

    @Operation(summary = "Obter uma comunidade por nome", description = "Endpoint para obter uma comunidade específica por seu nome.")
    @GetMapping("getName/{name}")
    public ResponseEntity<Optional<CommunityModel>> getByName(@PathVariable(value = "name") String name) {
        Optional<CommunityModel> community = Optional.ofNullable(communityService.findByName(name));

        if (community.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Optional.empty());
        }

        return ResponseEntity.status(HttpStatus.OK).body(community);
    }

    @Operation(summary = "Buscar comunidades por descrição", description = "Endpoint para buscar comunidades por sua descrição.")
    @GetMapping("getDescription/{description}")
    public List<CommunityModel> searchCommunitiesByDescription(@RequestParam String description) {
        return communityService.searchByDescription(description);
    }

    @Operation(summary = "Inserir uma nova comunidade", description = "Endpoint para criar e salvar uma nova comunidade.")
    @PostMapping("post")
    public ResponseEntity<Object> insert(@RequestBody @Valid CommunityDTO communityDTO){
        var communityModel = new CommunityModel();
        BeanUtils.copyProperties(communityDTO, communityModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(communityService.create(communityModel));
    }

    @Operation(summary = "Excluir uma comunidade por ID", description = "Endpoint para excluir uma comunidade específica por seu ID.")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID communityId){
        communityService.delete(communityId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualizar uma comunidade por ID", description = "Endpoint para atualizar as informações de uma comunidade existente por seu ID.")
    @PutMapping("deleteAll")
    public ResponseEntity<CommunityModel> update(@PathVariable UUID communityId, @RequestBody CommunityModel obj) {
        obj = communityService.update(communityId, obj);
        return ResponseEntity.ok().body(obj);
    }

}
