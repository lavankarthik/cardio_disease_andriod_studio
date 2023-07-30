# 1. Library imports
import uvicorn
from fastapi import FastAPI


from fastapi.middleware.cors import CORSMiddleware
import pickle


# 2. Create the app object
app = FastAPI()

app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

#load the model
rgModel = pickle.load(open("model.pkl", "rb"))


# 4. Index route, opens automatically on http://127.0.0.1:80
@app.get('/')
def index():
    return {'message': 'Hello World'}

@app.get("/predicttarget")
def getPredicttarget(age: int , sex: int , cp: int, trestbps: int  ,chol: int, fbs: int ,restecg: int , thalach: int , exang: int ,oldpeak: float , slope: int, ca: int, thal :int ):
    
    
    prediction = rgModel.predict([[age,sex,cp,trestbps,chol,fbs,restecg,thalach,exang,oldpeak,slope,ca,thal]])
    val = prediction[0];
    print(val);

    #return outcome
    return {
        'message':str(val)
    }


# 5. Run the API with uvicorn
#    Will run on http://127.0.0.1:80
if __name__ == '__main__':
    uvicorn.run(app, port=80, host='0.0.0.0')
    
#uvicorn app:app --reload
#http://127.0.0.1/predicttarget?age=68&sex=1&cp=3&trestbps=145&chol=233&fbs=1&restecg=0&thalach=150&exang=0&oldpeak=2.3&slope=0&ca=0&thal=1
