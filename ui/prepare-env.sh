#!/bin/bash


DIRETORIO_ENV=../.resources/environments/.env
DIR_ENV_RUN=./src/assets/env.run-time.js
DIR_ENV_APP=./src/assets/env.js
DIR_ENV_TEMP=./src/assets/env.template.js
DIR_POM=pom.xml
KEY_VERSION='${WEBSITE_VERSION}'

rm -rf $DIR_ENV_RUN
cp  $DIR_ENV_TEMP $DIR_ENV_RUN

## Ler a versão do arquivo pom.xml e atualiza variavél de ambiente
version=$(xmllint --xpath '/*[local-name()="project"]/*[local-name()="version"]/text()' "$DIR_POM")
sed -i 's,'"$KEY_VERSION"','$version',' "$DIR_ENV_RUN"

## Ler arquivo .env e atualiza variaveis de ambiente
while read -r line || [[ -n "$line" ]];
do
  varname=$(printf '%s\n' "$line" | sed -e 's/=.*//')
  replace=$(printf '%s\n' "$line" | sed -e 's/^[^=]*=//')
  find='${'$varname'}'
  sed -i 's,'"$find"','$replace',' "$DIR_ENV_RUN"
done < $DIRETORIO_ENV

envsubst < $DIR_ENV_RUN > $DIR_ENV_APP
rm -rf $DIR_ENV_RUN

cat $DIR_ENV_APP
