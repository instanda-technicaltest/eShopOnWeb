import * as dotenv from 'dotenv'

export const getEnv = () => {

    let originalPath = '../tests/' + process.env.ENV + '.env'
    console.log("originalPath::" + originalPath);
    dotenv.config({
        override: true,
        path: originalPath
    })
    console.log("Entering " + process.env.MESSAGE);
}