/*Prueba que al hacer click en el botón "add", verifique que se abre una ventana modal o un formulario para 
agregar una nueva tarea, y luego complete el formulario y verifique que se agrega correctamente 
a la lista de tareas.*/
describe('Añadir funcionalidades a las task', () => {
  it('añadir nuevas task a la lista', () => {
    cy.visit('/');//me falta añadir la URL

    cy.get('.addButton button').click(); // click the add button

    // check that the modal or form for adding a new task is displayed
    cy.get('.modal').should('be.visible'); 
    cy.get('.form-control').type('My new task'); // type the task name in the form

    cy.get('.submit-button').click(); // submit the form

    // check that the new task is displayed in the task list
    cy.get('.task-list').should('contain', 'My new task');
  });
});
