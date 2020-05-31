import requests
from bs4 import BeautifulSoup as bs
import json

resposta = requests.get("https://www.ssp.sp.gov.br/Estatistica/ViolenciaMulher.aspx")
container_fluid = bs(resposta.content.decode(), 'html.parser').findAll("div", {"class", "container-fluid"})
divs = container_fluid[1].findAll("div")
eventos = []
for div in divs:
    try:
        if str(div["id"]).__contains__("conteudo_repPeriodo_divPeriodo_"):
            mes_ano = div.find("div").find("b").find("span").getText().replace("Ocorrências Registradas no mês: ", "")
            ano = int(mes_ano.split(" de ")[1])
            mes = mes_ano.split(" de ")[0]
            first_tr = True
            evento = { "ano": ano, "mes": mes, "ocorrencias" : [] }
            for tr in div.find("table").findAll("tr"):
                if first_tr:
                    first_tr = False
                    continue
                descricao = tr.findAll("td")[0].getText()
                quantidade = int(tr.findAll("td")[4].getText())
                evento["ocorrencias"].append({ "descricao" : descricao, "quantidade" : quantidade })
        eventos.append(evento)
    except:
        pass
print(json.dumps(eventos))