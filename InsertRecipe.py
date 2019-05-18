import pymongo as myDB
import sys


def insert_reipe(name:str, ingredients:list, pre:str):
    mydict = {"name": name, "ingredients": ingredients, "preparation" : pre}
    mycollection.insert_one(mydict)


name = sys.argv[1]
ingre = sys.argv[2]
ingreArr = ingre.split(",")
prep = sys.argv[3]
new_recipe = {
    "name" : name,
    "ingredients" : ingreArr,
    "preparation" : prep
}

myclient = myDB.MongoClient('localhost:27017')
mydb = myclient['mydb']
mycollection = mydb["recipe"]
mycollection.insert(new_recipe)
print(new_recipe)
sys.exit(0)



# x =mydb.mycollection.find({"ingredients": {'$regex': '/m/'}})
# print(x)
# print(list(mycollection.find()))
#    mydoc = list(mycollection.find({"ingredients": {"$all": ingredientsToFind}}, {"_id": 0, "preparation": 0}))
# print(list(mycollection.find({"ingredients": {"$all": ingredientsToFind}})))

# for i in mydoc:
#    print(i["name"], "-", i["ingredients"], end="\n", sep=" ")