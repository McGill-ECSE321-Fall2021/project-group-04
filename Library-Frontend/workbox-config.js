module.exports = {
	globDirectory: 'public/',
	globPatterns: [
		'**/*.{vue,png,scss,css,txt,eot,svg,ttf,woff,woff2,js}'
	],
	swDest: 'public/sw.js',
	ignoreURLParametersMatching: [
		/^utm_/,
		/^fbclid$/
	]
};