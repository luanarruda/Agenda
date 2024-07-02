import { ContatoService } from './../contato.service';
import { Component, OnInit } from '@angular/core';
import { Contato } from '../contato';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-contatos',
  templateUrl: './contatos.component.html',
  styleUrl: './contatos.component.css'
})
export class ContatosComponent implements OnInit{
  contatos: Contato[] = [];
  formGroupContato:FormGroup;
  isEditing: boolean = false;
  submited: boolean = false;

  constructor(private formBuilder: FormBuilder, private service: ContatoService){
    this.formGroupContato = formBuilder.group({
      id: [''],
      name: ['', [Validators.required]],
      telefone: ['', [Validators.required, Validators.pattern(/^[0-9]+$/)]],
      email: ['', Validators.email],
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
    this.submited = true;
    if (this.formGroupContato.valid){
      if(this.formGroupContato.valid){
        if(this.isEditing){
        this.service.update(this.formGroupContato.value).subscribe({
          next: () => {
            this.loadContatos();
            this.isEditing = false;
            this.submited = false;
          }
        });
      }
      else{
        this.service.save(this.formGroupContato.value).subscribe({
          next: (data)  => {this.contatos.push(data);
            this.submited = false;
          }
        });
     }
     this.formGroupContato.reset();
      }
    }

  }


  delete(contato:Contato){
    this.service.delete(contato).subscribe({
      next: () => this.loadContatos()
    });
  }

  edit(contato:Contato){
    this.formGroupContato.setValue(contato);
    this.isEditing = true;
  }

  get name(): any{
    return this.formGroupContato.get("name");
  }
  get telefone(): any{
    return this.formGroupContato.get("telefone");
  }
  get email(): any{
    return this.formGroupContato.get("email");
  }

}
