module.exports = {
	globDirectory: 'src/',
	globPatterns: [
		'**/*.{vue,png,scss,css,txt,eot,svg,ttf,woff,woff2,js}'
	],
	swDest: 'public/sw.js',
	ignoreURLParametersMatching: [
		/^utm_/,
		/^fbclid$/
	]
};