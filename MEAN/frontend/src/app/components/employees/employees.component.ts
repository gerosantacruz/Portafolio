import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Employee } from 'src/app/models/employee';
import { EmployeeService } from 'src/app/services/employee.service';


declare var M: any;

@Component({
  selector: 'app-employees',
  templateUrl: './employees.component.html',
  styleUrls: ['./employees.component.css'],
  providers: [ EmployeeService ]
})
export class EmployeesComponent implements OnInit {

  constructor(private employeeService: EmployeeService) { }

  ngOnInit() {
    this.getEmployees();
  }

  addEmployee(form: NgForm){
    if(form.value._id){
      this.employeeService.putEmployee(form.value)
      .subscribe(res => {
        this.resetForm(form);
        M.toast({html: 'Update successfully'});
        this.getEmployees();

      })
    } else {
    this.employeeService.postEmployees(form.value)
      .subscribe(res => {
          this.resetForm(form);
          M.toast({html: 'Save successfully'});
          this.getEmployees();
      })}
  }
  
  getEmployees(){
    this.employeeService.getEmployees()
    .subscribe(res => {
      this.employeeService.employees = res as Employee[];
      console.log(res);

    })
  }

  editEmployee(employee: Employee){
    this.employeeService.selectedEmployee = employee;
    
  }

  deleteEmployee(_id:string){
    if(confirm('Are you sure do you want to delete this record'){
      this.employeeService.deleteEmployee(_id)
      .subscribe(res => {
        this.getEmployees();
    });
    
        
    }
  }

  resetForm(form?: NgForm){
    if(form){
      form.reset();
      this.employeeService.selectedEmployee = new Employee();
    }
  }
}
