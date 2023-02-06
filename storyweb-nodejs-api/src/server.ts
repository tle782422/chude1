import * as tsImport from 'ts-import';
import express, {Express} from 'express';
import * as http from "http";
import * as homeController from './controllers/home-controller'
import * as countryController from './controllers/country-controller'
import cors from 'cors';

const router: Express = express();
const app = express();
//const cors = require('cors')

router.use('',
    homeController.default,
    countryController.default
)
const allowedOrigins = ['http://localhost:4200'];
const options: cors.CorsOptions = {
    origin: allowedOrigins
};
router.use(cors(options))
app.use(cors(options));

app.use(express.json());

//netstat  -ano  |  findstr  8881
//taskkill  /F  /PID  10552
const httpServer = http.createServer(router)
httpServer.listen(8881,()=>{
    console.log('Server is running port 8881')
})

