package com.example.testapi.controller;

import com.example.testapi.exception.ResourceNotFoundException;
import com.example.testapi.model.Player;
import com.example.testapi.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PlayerController {

    @Autowired
    PlayerRepository playerRepository;

    // Get All Player
    @GetMapping("/players")
		public List<Player> getAllPlayers() {
		    return playerRepository.findAll();
	}

    // Create a new Player
    @PostMapping("/players")
		public Player createPlayer(@Valid @RequestBody Player player) {
		    return playerRepository.save(player);
	}

    // Get a Single Player
    @GetMapping("/players/{id}")
		public Player getPlayerById(@PathVariable(value = "id") Long playerId) {
		    return playerRepository.findById(playerId)
		            .orElseThrow(() -> new ResourceNotFoundException("Player", "id", playerId));
	}

    // Update a Player
    @PutMapping("/players/{id}")
		public Player updatePlayer(@PathVariable(value = "id") Long playerId,
		                                        @Valid @RequestBody Player playerDetails) {

		    Player player = playerRepository.findById(playerId)
		            .orElseThrow(() -> new ResourceNotFoundException("Player", "id", playerId));

		    player.setFirstName(playerDetails.getFirstName());
		    player.setLastName(playerDetails.getLastName());
		    player.setPosition(playerDetails.getPosition());

		    Player updatedPlayer = playerRepository.save(player);
		    return updatedPlayer;
	}

    // Delete a Player
    @DeleteMapping("/players/{id}")
		public ResponseEntity<?> deletePlayer(@PathVariable(value = "id") Long playerId) {
		    Player player = playerRepository.findById(playerId)
		            .orElseThrow(() -> new ResourceNotFoundException("Player", "id", playerId));

		    playerRepository.delete(player);

		    return ResponseEntity.ok().build();
	}
}