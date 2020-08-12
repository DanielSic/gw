## Comandi base di git
### Quando si inizia a lavorare
* Se si hanno modifiche locali in corso usare `git fetch`
* Altrimenti usare `git pull`
* Creare un nuovo branch se non lo si ha già `git checkout -b nome_del_branch`

### Quando si finisce di lavorare
* Dal nuovo branch `git push origin nome_del_branch`
* Se si  ritiene che il codice sia a posto creare una pull request su github
  * Nella descrizione della pull request col master spiegare cosa si è modificato/aggiunto/etc
  * Inserire tra i reviewers gli altri membri del gruppo
  * In fondo alla pagina si trova il tasto merge
  * In caso di conflitti si possono risolvere direttamente su github ma conviene farlo assieme
  
