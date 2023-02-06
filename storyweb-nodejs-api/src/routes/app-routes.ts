import express from 'express'
import controller from '../controllers/home-controller'
import countryController from '../controllers/country-controller'

const router = express.Router();
var  cors = require('cors')
var app = require('express')();

// Allow all
app.options('/home', cors())
app.options('/countries', cors())
router.use(cors());
router.get('/home', controller);
router.get('/countries', countryController)

export = router;