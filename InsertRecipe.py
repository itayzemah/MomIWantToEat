import pymongo as myDB
import sys
import numbers

def insert_reipe(name:str, ingredients:list,quants : list, pre:str):
    mydict = {"name": name, "ingredients": ingredients,"quantities" : quants ,"preparation" : pre}
    mycollection.insert_one(mydict)


name = sys.argv[1]
ingre = sys.argv[2]
ingreArrNames = [x for x in ingre.split(",") if x is not '' or not ' ']
ingreQuan = sys.argv[3]
ingreArrQuan = [float(x) for x in ingreQuan.split(",") if x is not '' or not ' ']
prep = sys.argv[4]
new_recipe = {
    "name" : name,
    "ingredients names" : ingreArrNames,
    "ingredients quantities" :ingreArrQuan,
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