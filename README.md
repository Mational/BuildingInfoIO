# Building Info ![example workflow](https://github.com/Mational/BuildingInfoIO/actions/workflows/ci.yml/badge.svg)

## Opis projektu

***Dla administratorów budynków, którzy pragną optymalizować koszty zarządzania budynkami
nasza aplikacja Building Info umożliwi pozyskanie informacji o parametrach budynku na poziomie
pomieszczeń, kondygnacji oraz całych budynków.
Aplikacja będzie dostępna poprzez GUI a także jako zdalne API dzięki czemu można ją zintegrować z istniejącymi narzędziami.***


## Struktura danych:

* Lokacja to budynek, poziom, lub pomieszczenie

* Budynek może składać się z poziomów a te z pomieszczeń

* Każda lokalizacja jest charakteryzowana przez:

  * id – unikalny identyfikator
   
  * name – opcjonalna nazwa lokalizacji
   
* Pomieszczenie dodatkowo jest charakteryzowane przez:
  
  * area = powierzchnia w m^2
   
  * cube = kubatura pomieszczenia w m^3
   
  * heating = poziom zużycia energii ogrzewania (float)
  
  * light – łączna moc oświetlenia

## Przykładowe wejście w formacie JSON

```json
[
 {"min": "15.0"},
	{"max": "12.2"},
	{
		"id": "1",
		"name": "building",
		"levels": [{
				"id": "1.1",
				"name": "floor1",
				"rooms": [{
						"id": "1.1.1",
						"name": "room101",
						"area": 14.2,
						"cube": 28.4,
						"heating": 13.2,
						"light": 15
					},
					{
						"id": "1.1.2",
						"name": "room102",
						"area": 15.2,
						"cube": 30.4,
						"heating": 14.2,
						"light": 16
					}
				]
			},
			{
				"id": "1.2",
				"name": "floor2",
				"rooms": [{
						"id": "1.2.1",
						"name": "room201",
						"area": 14.5,
						"cube": 29,
						"heating": 13.6,
						"light": 15.3
					},
					{
						"id": "1.2.2",
						"name": "room202",
						"area": 12.2,
						"cube": 24.4,
						"heating": 10.2,
						"light": 13
					}
				]
			}
		]
	}
]
```

## Ścieżki i metody obsługiwane przez REST

* Ścieżki obsługujące metodę [POST]:
  * **BuildingInfoService** - służy do przesyłania struktury budynku w formacie json
* Ścieżki obsługujące metodę [GET]:
  * **BuildingInfoService** - pokazuje strukturę budynku w formacie json 
  * **BuildingInfoService/{option}** - pokazuje informacje o budynku w formacie json
  * **BuildingInfoService/floors/{option}** - pokazuje informacje o wszystkich piętrach w formacie json
  * **BuildingInfoService/floors/{floor_name}/{option}** - pokazuje informacje o danym piętrze w formacie json
  * **BuildingInfoService/rooms/{option}** - pokazuje informacje o wszystkich pokojach w budynku w formacie json
  * **BuildingInfoService/rooms/{room_name}/{option}** - pokazuje informacje o danym pokoju w formacie json

**{option}** - wartość ze zbioru {"all", "area", "cube", "light", "heating", "lps", "hpc"}  
**{floor_name}** - nazwa piętra wzięta z atrybutu "name" z json'a  
**{room_name}** - nazwa pokoju wzięta z atrybutu "name" z json'a

## Akcje naprawcze po pierwszym Sprintem #1

* Co poprawić ?
  * lepszy podział obowiązków w projekcie
* Co wprowadzić ?
  * spotkania poza projektowe
* Co usunąć ?
  * rozmowy na niepowiązane tematy podczas pracy
