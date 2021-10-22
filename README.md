#DNA Detector

Esta es una aplicacion desarrollada en *Java 11* usando *spring boot 2.6.0* y *Maven*. La aplicacion esta hosteada en AWS 
usando *elasticbeanstalk*

## Ejecucion

Para ejecutar el servicio de prueba se requiere hacer un POST request a la URL
/mutant/ con un request como se muestra acontinuacion.


    curl --location --request POST '<application_url>/mutant/' \
    --header 'Content-Type: application/json' \
    --data-raw '{
    "dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
    }'

En caso de que la validacion del ADN sea positiva se devuelve un status code de 200 en caso contrario se devuelve un 
status code de 403.
