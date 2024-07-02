import { ContatoService } from './../contato.service';
import { Component, OnInit } from '@angular/core';
import { Contato } from '../contato';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-contatos',
  templateUrl: './contatos.component.html',
  styleUrl: './contatos.component.css'
})
export class ContatosComponent implements OnInit{
  contatos: Contato[] = [];
  formGroupContato:FormGroup;
  isEditing: boolean = false;

  constructor(private formBuilder: FormBuilder, private service: ContatoService){
    this.formGroupContato = formBuilder.group({
      id: [''],
      name: [''],
      telefone: [''],
      email: [''],
      endereco: [''],
      categoria: [''],
      aniversario: [''],
      genero: [''],
      favorito: [false],
    });
  }
  ngOnInit(): void {
    this.loadContatos();
  }

  loadContatos(){
    this.service.getContatos().subscribe({next: data => {this.contatos = data; console.log(data)}});
  }

  save(){
    if(this.isEditing){
      this.service.update(this.formGroupContato.value).subscribe({
        next: () => {
          this.loadContatos();
          this.isEditing = false;
        }
      });
    }
    else{
      this.service.save(this.formGroupContato.value).subscribe({
        next: data => this.contatos.push(data)
      });
	 }}


  delete(contato:Contato){
    this.service.delete(contato).subscribe({
      next: () => this.loadContatos()
    });
  }

  edit(contato:Contato){
    this.formGroupContato.setValue(contato);
    this.isEditing = true;
  }
}
