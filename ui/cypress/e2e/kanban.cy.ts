//AÑADIR UN NUEVO KANBAN
describe('Añadir Kanban', () => {
  it('debe mostrar un formulario de nuevo Kanban al hacer clic en el botón Añadir y permitir añadir un nuevo Kanban', () => {
    cy.visit('/kanbans'); // Visitar la página de Kanban

    cy.get('addButton[mat-fab]').click(); // Hacer clic en el botón Añadir

    // Verificar que se muestra el formulario de nuevo Kanban
    cy.get('.form-new-kanban').should('be.visible');

    // Rellenar el formulario de nuevo Kanban
    cy.get('input[name=title]').type('Mi nuevo Kanban');
    cy.get('textarea[name=description]').type('Una descripción para mi nuevo Kanban');

    // esto debería hacer click en el botón
    cy.get('button[type=buttonSave]').click();

    // Verificar que se ha añadido el nuevo Kanban a la lista
    cy.get('.kamba-card').should('have.length', 1); // La lista debe tener un solo elemento
    cy.get('.kamba-card mat-card-title').should('have.text', 'Mi nuevo Kanban');
  });
});

