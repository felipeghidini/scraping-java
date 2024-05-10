# Teste Backend Java Pleno 

## Steps do desafio
###
1 - Utilizando a biblioteca JSOUP, fazer a leitura e armazenamento em um banco de dados os status dos serviços de Nota Fiscal Eletronica da página da fazenda.
###
2 - Desenvolver um JOB que seja executado a cada 5 minutos para armazenar um histórico de status dos serviços.
###
3- Retornar por rest os status atual dos serviços por estado.
###
4- Retornar por rest o status atual do serviço filtrando por estado.
###
5- Retornar por rest os status dos serviços por estado filtrando por data. 
####
6- Retornar por rest qual estado teve mais indisponibilidade de serviço.

## Steps 1 e 2
### Utilizei o Postgres como Banco de Dados e executei o JOB com o @Scheduled do Spring
![image](https://github.com/felipeghidini/scraping-java/assets/49081674/8723a38a-d655-4c4c-afc6-f37a4e4d4be4)

## Step 3
### Get no Status atual de todos os Estados
![image](https://github.com/felipeghidini/scraping-java/assets/49081674/cd9655f9-ee2d-4aba-86bb-c3d79d91d511)

## Step 4
### Get no Status filtrando pelo Estado
![image](https://github.com/felipeghidini/scraping-java/assets/49081674/9f345f07-89cc-4aea-a36a-08bcffd8f13a)

## Step 5
### Get no Status filtrando pelo Estado e pela Data
![image](https://github.com/felipeghidini/scraping-java/assets/49081674/929b203d-de0e-4403-8d28-1ebb5e77ef0d)

### Step 6
## Get no Status filtrando pela INDISPONIBILIDADE
![image](https://github.com/felipeghidini/scraping-java/assets/49081674/ba07aa8f-b249-4250-a3d7-5206211f916b)
