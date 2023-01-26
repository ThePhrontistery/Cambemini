//test relacionados con archivos adjuntos
describe("test de atatchemnt", () => {
  beforeEach(() => {
    cy.login();
    cy.kanban();
  });
  //adjuntar un archivo a la nota 1 del swimlane 1
  it("adjuntar un archivo pdf", () => {
    const filePath = "./document.pdf";
    cy.get(
      "#3  > mat-card-content > div.file.upload > div.space-between > button"
    ).click();

    cy.get('input[type="file"][id="file3"]')
      .invoke("css", "display", "block")
      .selectFile(
        {
          contents: Cypress.Buffer.from(filePath),
          fileName: "document.pdf",
          mimeType: "application/pdf",
          lastModified: Date.now(),
        },
        { force: true }
      );
    cy.get('input[type="file"][id="file3"]').should(
      "have.value",
      "C:\\fakepath\\document.pdf"
    );
  });

  it("Descargar el archivo", () => {
    cy.get('.flex-start > :nth-child(3) > .mat-button-wrapper > .mat-icon').click();
    cy.readFile('./cypress/downloads/document.pdf').should('exist');
  });

  it("Visualizar el archivo", ()=>{
    cy.get('.flex-start > :nth-child(2) > .mat-button-wrapper > .mat-icon').click();
    //falta la aserción ¿se visualiza el archivo?
    //cerrar el archivo
  });
});
