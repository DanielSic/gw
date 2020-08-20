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


## Comandi di java
 *Da effettuarsi dalla cartella superiore*
* Compilare con `javac gw/*.java`
* Eseguire con `java gw.testa`  
* Impacchettare con `jar cvfm GravityWars.jar gw/manifest.mf gw/*.class`
* Si può eseguire sia con `java -jar GravityWars.jar` che con il doppio click
