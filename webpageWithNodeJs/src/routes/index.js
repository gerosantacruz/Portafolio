const express = require('express');
const router = express.Router();

//routes
router.get('/', (req, res) => {
    res.render('index', {title: 'Page with NodeJs'})
});

router.get('/contact', ( req, res) => {
    res.render('contact', {title: 'Contact Page'})
});

module.exports = router;