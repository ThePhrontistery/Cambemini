//test relacionados con archivos adjuntos
describe("test de atatchemnt", () => {
  beforeEach(() => {
    cy.login();
    cy.kanban();
  });
  //adjuntar un archivo pdf a la nota 1 del swimlane 1
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

  it("Descargar el archivo pdf", () => {
    cy.get("#attachmentDownload5").click();
    cy.readFile("./cypress/downloads/document.pdf").should("exist");
  });

  it("Visualizar el archivo pdf", () => {
    cy.get("#attachmentViewFile5").click();
    cy.get(
      "#mat-dialog-0 > app-attachment-viewer > div.ng-star-inserted > pdf-viewer > div"
    ).should("be.visible");
    //espero 3 segundos
    cy.wait(3000);
    //cerrar el archivo
    cy.get(
      "#mat-dialog-0 > app-attachment-viewer > div.space-between > button > span.mat-button-wrapper > mat-icon"
    ).click();
  });

  //eliminar el archivo pdf, pero no aceptar al final
  it("Eliminar el archivo pdf, pincho en No", () => {
    cy.get("#attachmentDelete5 > .mat-button-wrapper > .mat-icon")
      .should("be.visible")
      .click();
    //no acepto el formulario
    cy.contains("No").click();
    //espero 1 segundo
    cy.wait(1000);
    //verifico que el archivo si existe el archivo todavía
    cy.get(
      "#3  > mat-card-content > div.file.upload > div.ng-star-inserted > div:nth-child(2) > div > div:nth-child(1)"
    ).should("be.visible");
  });

  //elimino por completo el pdf
  it("Eliminar el archivo pdf", () => {
    cy.get("#attachmentDelete5 > .mat-button-wrapper > .mat-icon")
      .should("be.visible")
      .click();
    //acepto el formulario
    cy.contains("Sí").click();
    //espero 1 segundo
    cy.wait(1000);
    //verifico que no existe el documento
    cy.contains("document.pdf").should("not.exist");
  });

  //adjuntar un archivo de imagen a la nota 1 del swimlane 2
  it("adjuntar una imagen", () => {
    const filePath = "./task.jpg";
    cy.get(
      "#1  > .mat-card-content > .file > .space-between > .mat-focus-indicator > .mat-button-wrapper > .mat-icon"
    ).click();

    cy.get('input[type="file"][id="file1"]')
      .invoke("css", "display", "block")
      .selectFile(
        {
          contents: Cypress.Buffer.from(filePath),
          fileName: "task.jpg",
          mimeType: "image/jpg",
          lastModified: Date.now(),
        },
        { force: true }
      );
    cy.get('input[type="file"][id="file1"]').should(
      "have.value",
      "C:\\fakepath\\task.jpg"
    );
  });

  it("Descargar la imagen", () => {
    cy.get("#attachmentDownload6").click();
    cy.readFile("./cypress/downloads/task.jpg").should("exist");
  });

  it("Visualizar el archivo de imagen", () => {
    cy.get("#attachmentViewFile6").click();
    cy.get("#mat-dialog-0").should("be.visible");
    //espero 3 segundos
    cy.wait(3000);
    //cerrar el archivo
    cy.get(
      "#mat-dialog-0 > app-attachment-viewer > div.space-between > button"
    ).click();
  });

  //eliminar el archivo de imagen jpg, pero no aceptar al final
  it("Eliminar la imagen, pincho en No", () => {
    cy.get("#attachmentDelete6 > .mat-button-wrapper > .mat-icon")
      .should("be.visible")
      .click();
    //no acepto el formulario
    cy.contains("No").click();
    //espero 1 segundo
    cy.wait(1000);
    //verifico que la imagen sigue existiendo
    cy.contains("task.jpg").should("be.visible");
  });

  //elimino por completo la imagen
  it("Eliminar la imagen", () => {
    cy.get("#attachmentDelete6 > .mat-button-wrapper > .mat-icon")
      .should("be.visible")
      .click();
    //acepto el formulario
    cy.contains("Sí").click();
    //espero 1 segundo
    cy.wait(1000);
    //verifico que no existe el documento
    cy.contains("task.jpg").should("not.exist");
  });

  //texto plano
  //adjuntar un archivo de texto plano a la nota 1 del swimlane 3
  it("adjuntar una txt", () => {
    const filePath = "./adjunto.txt";
    cy.get(
      "#4  > mat-card-content > div.file.upload > div.space-between > button"
    ).click();
    cy.get('input[type="file"][id="file4"]')
      .invoke("css", "display", "block")
      .selectFile(
        {
          contents: Cypress.Buffer.from(filePath),
          fileName: "adjunto.txt",
          mimeType: "text/plain",
          lastModified: Date.now(),
        },
        { force: true }
      );
    cy.get('input[type="file"][id="file4"]').should(
      "have.value",
      "C:\\fakepath\\adjunto.txt"
    );
  });

  it("Descargar el archivo txt", () => {
    cy.get("#attachmentDownload7").click();
    cy.readFile("./cypress/downloads/adjunto.txt").should("exist");
  });

  it("Visualizar el archivo txt", () => {
    cy.get("#attachmentViewFile7").click();

    cy.get("#mat-dialog-0").should("be.visible");
    //espero 3 segundos
    cy.wait(3000);
    //cerrar el archivo
    cy.get(
      "#mat-dialog-0 > app-attachment-viewer > div.space-between > button"
    ).click();
  });

  //eliminar el archivo de imagen jpg, pero no aceptar al final
  it("Eliminar la imagen, pincho en No", () => {
    cy.get("#attachmentDelete7 > .mat-button-wrapper > .mat-icon")
      .should("be.visible")
      .click();
    //no acepto el formulario
    cy.contains("No").click();
    //espero 1 segundo
    cy.wait(1000);
    //verifico que la imagen sigue existiendo
    cy.contains("adjunto.txt").should("be.visible");
  });

  //elimino por completo el pdf
  it("Eliminar la imagen", () => {
    cy.get("#attachmentDelete7 > .mat-button-wrapper > .mat-icon")
      .should("be.visible")
      .click();
    //acepto el formulario
    cy.contains("Sí").click();
    //espero 1 segundo
    cy.wait(1000);
    //verifico que no existe el documento
    cy.contains("adjunto.txt").should("not.exist");
  });
});
