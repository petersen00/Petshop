package com.example.ltp3.sqlite;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewUserActivity extends Activity {
	private Usuario usuario = new Usuario();
	private EditText nomeEt;
	private EditText emailEt;
	private EditText senhaEt;
	private Button salvarBt;
	private Button editarBt;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_user);
		
		nomeEt =  findViewById(R.id.nome);
		emailEt =  findViewById(R.id.email);
		senhaEt =  findViewById(R.id.senha);
		salvarBt =  findViewById(R.id.button1);
		editarBt =  findViewById(R.id.button2);
		
		
		Intent intent = getIntent();
		if(intent != null){
			Bundle bundle = intent.getExtras();
			if(bundle != null){
				
				usuario.setId(bundle.getLong("id"));
				usuario.setNome(bundle.getString("nome"));
				usuario.setEmail(bundle.getString("email"));
				
				nomeEt.setText(usuario.getNome());
				emailEt.setText(usuario.getEmail());
				
				senhaEt.setVisibility(View.GONE);
				salvarBt.setVisibility(View.GONE);
				editarBt.setVisibility(View.VISIBLE);
			}
		}
	}
	
	
	public void salvarUsuario(View view){
		usuario.setNome(nomeEt.getText().toString());
		usuario.setEmail(emailEt.getText().toString());
		usuario.setSenha(senhaEt.getText().toString());
		
		BD bd = new BD(this);
		bd.inserir(usuario);
		
		Toast.makeText(this, "Usu�rio inserido com sucesso!", Toast.LENGTH_SHORT).show();
	}
	
	
	public void editarUsuario(View view){
		usuario.setNome(nomeEt.getText().toString());
		usuario.setEmail(emailEt.getText().toString());
		
		BD bd = new BD(this);
		bd.atualizar(usuario);
		
		Toast.makeText(this, "Usu�rio \""+usuario.getNome()+"\" atuailizado com sucesso.", Toast.LENGTH_SHORT).show();
	}

}
