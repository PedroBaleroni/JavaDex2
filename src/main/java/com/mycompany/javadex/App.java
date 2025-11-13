package com.mycompany.javadex;

import javafx.application.Application;
import javafx.scene.Scene;
// import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import classes.Pokemon;
import classes.Tipo;
import database.database;
import java.util.List;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.image.*;


/**
 * JavaFX App
 */
public class App extends Application {
     // Variaveis Globais
    private Label poke;
    private Pokemon pokemonEscolhido;
    private Image obj;
    private ImageView iv;
    
    @Override
    public void start(Stage stage) {
        database bd = new database();
        bd.inicialize();
        
        poke = new Label("JavaDex");
        poke.setMinWidth(300);
        poke.setAlignment(Pos.CENTER);

        VBox listaDePokemons = new VBox();
        listaDePokemons.setMinWidth(200);
        listaDePokemons.setAlignment(Pos.CENTER);
        listaDePokemons.setSpacing(3);
        for(int i=1; i<=151; i++){
            Pokemon laco_repeticao = bd.buscaPokemon(i);
            Button botao = new Button (laco_repeticao.getNome());
            botao.setOnAction(e->atualizacao(laco_repeticao));
            botao.setMinWidth(120);
            listaDePokemons.getChildren().add(botao);
        }
        ScrollPane scroll_lista = new ScrollPane();
        scroll_lista.setContent(listaDePokemons);
        scroll_lista.setMinWidth(200);
        
        obj = new Image(getClass().getResourceAsStream("/image/poke_nada_2.png"));
        iv = new ImageView(obj);
        iv.setFitWidth(300); //Largura para 200px
        iv.setPreserveRatio(true); // Manter Dimensões
        
        HBox tela_principal = new HBox();
        tela_principal.getChildren().add(scroll_lista);
        VBox apresentacao = new VBox();
        apresentacao.setMinWidth(340);
        apresentacao.setAlignment(Pos.TOP_CENTER);
        apresentacao.getChildren().add(poke);
        apresentacao.getChildren().add(iv);
        tela_principal.getChildren().add(apresentacao);
        var scene = new Scene(tela_principal,  640, 480);
        stage.setScene(scene);
        stage.show();
    }
    public void atualizacao(Pokemon p){
        poke.setText(p.getNome());
        iv.setImage(new Image(getClass().getResourceAsStream("/image/"+ String.format("%03d", p.getNumero())+".png")));
    }

    public static void main(String[] args) {
        launch();
    }

}