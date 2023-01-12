describe("test relacionados con kanbans", () => {
  //defino una prueba individual de creación de un kanban
  it("Debe añadir un kanban a un usuario", () => {
    cy.login();
    //verifico que el botón es visible
    cy.get("button[mat-fab][type=addButton]").should("be.visible");

    cy.get("button[mat-fab][type=addButton]").click(); // Hacer clic en el botón Añadir

    // Verificar que se muestra el formulario de nuevo Kanban
    cy.get("form").should("be.visible");

    // Rellenar el formulario de nuevo Kanban
    cy.get("input[name=title]").type("Mi nuevo Kanban");
    cy.get("textarea[name=description]").type(
      "Una descripción para mi nuevo Kanban"
    );

    // esto debería hacer click en el botón
    cy.get("body").click();
    //cy.get('button[type=buttonSave]').click();

    //verifica que la url actual del navegador contenga "/kanbans", si es así se considera una prueba exitosa, sino, falla
    cy.url().should("contain", "/kanbans");
  });

  //comprobar que al menos haya un kanban
  it("comprobar que exista un kanban", () => {
    cy.login()
    cy.get("mat-card").contains("My first Kanban");
  });

  //hacer click en un kanban
  it('Hacer click en My first Kanban', ()=>{
    cy.login()
    cy.get("mat-card").contains("My first Kanban").click()
    //verifica que la url actual del navegador sea la que quiero
    cy.url().should("contain", "/kanbans/1");
  });

  //Cambiar a un editor y hacerle owner, por ejemplo, y guardar
  it('Cambio de rol y guardar', ()=>{
    cy.login()
    cy.get("mat-card").contains("My first Kanban").click()
    cy.get('.justify-content-center > :nth-child(2)').click()
    //verificar que sale el mat dialog
    cy.get("mat-dialog-container").should("be.visible")
    //elegir del selector
    cy.get('mat-select').click()
    cy.get('mat-option').contains('Owner').click()
    cy.contains('Guardar').click()
  });

  //cambiar de rol, pero dar al botón salir
  it('Cambio de rol y dar al botón salir', ()=>{
    cy.login()
    cy.get("mat-card").contains("My first Kanban").click()
    cy.get('.justify-content-center > :nth-child(2)').click()
    //verificar que sale el mat dialog
    cy.get("mat-dialog-container").should("be.visible")
    //elegir del selector
    cy.get('mat-select').click()
    cy.get('mat-option').contains('Owner').click()
    cy.contains('Cerrar').click()
  });
});
