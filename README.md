# Identity Server

Aplicação sso responsável por gerir os usuários, perfils e autenticação/autorização. 

## Estrutura do projeto

``` text

├── .github
│   └── workflows
│       └── script
│           └── manifest.sh
│       └── build.yml
│       └── deploy.yml
├── app
│   └── api
│   └── ui
├── docs
│   └── CONTRIBUTING.md
│   └── CODE_OF_CONDUCT.md
│   └── PULL_REQUEST_TEMPLATE.md
└── README.md
```

## Fluxo de Trabalho - Deploy Ambientes

O fluxo de trabalho são processos definidos para dar direção a etapa de desenvolvimento , homologação e lançamento.

As alterações são aplicadas em determinado ambiente através das **branchs**, ao publicar uma alteração numa das branchs ***principais*** o pipeline executa a atualização, a tabela abaixo descreve a relação entre branch e o respectivo ambiente:

### Tabela Branch x Ambiente

| Branch | Ambiente |
| --- | --- |
| develop | Aplica no ambiente __DSV__ |
| release/** | Aplica no ambiente __HMG__ |
| pre-release/** | Aplica no ambiente __STG__ |
| main | Aplica no ambiente __PRD__ |

``` sh
cd app &&
mvn clean install -Dmaven.test.skip=true -Dmaven.javadoc.skip=true &&
docker compose --env-file .env up -d --build --force-recreate
```

## Licença

> [!IMPORTANT]
> *O código fonte neste projeto não possui licença de uso.*

É terminantemente proibido reproduzir, distribuir, alterar, utilizar engenharia reversa ou valer-se de qualquer tentativa de reverter ao seu código-fonte qualquer dos componentes que compõem o SOFTWARE, bem como utilizar subterfúgios para burlar a quantidade de usuários licenciados.
