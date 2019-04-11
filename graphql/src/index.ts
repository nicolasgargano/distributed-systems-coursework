const {postgraphile} = require("postgraphile")
const http = require("http")
const express = require("express")
const expressPlayground = require('graphql-playground-middleware-express').default
const PgSimplifyInflectorPlugin = require("@graphile-contrib/pg-simplify-inflector")


process.env.DEBUG = "postgraphile:graphql,postgraphile:request,postgraphile:postgres*"
let postgraphileServer =

    postgraphile(process.env.DATABASE_URL, "public",
        {
            watchPg: true,
            pgDefaultRole: 'anonymous',
            setofFunctionsContainNulls: true,
            ignoreRBAC: false,
            extendedErrors: ['detail', 'hint', 'errcode'],
            // extendedErrors: ['severity', 'code', 'detail', 'hint', 'positon', 'internalPosition', 'internalQuery', 'where', 'schema', 'table', 'column', 'dataType', 'constraint', 'file', 'line', 'routine'],
            simpleCollections: "only",
            legacyRelations: 'omit',
            graphqlRoute: "/graphql",
            graphiqlRoute: "/graphiql",
            graphiql: true,
            enhanceGraphiql: true,
            enableCors: true,
            jwtSecret: 'kit',
            jwtPgTypeIdentifier: 'public.jwt_token',
            appendPlugins: [PgSimplifyInflectorPlugin],
            // showErrorStack: true,
            graphileBuildOptions: {
                /*
                 * Uncomment if you are using `simpleCollections: 'only'` and you never
                 * want relay connections
                 */
                pgOmitListSuffix: true,
                /*
                 * Uncomment if you want 'userPatch' instead of 'patch' in update
                 * mutations.
                 */
                //pgSimplifyPatch: false,
                /*
                 * Uncomment if you want 'allUsers' instead of 'users' at root level.
                 */
                //pgSimplifyAllRows: false,
            },
        }
    );

const app = express()
app.use(function(req: any, res: any, next: any) {
    console.log(req.headers);
    next();
})
app.use(postgraphileServer)
app.get('/playground', expressPlayground({ endpoint: '/graphql' }))
app.listen(process.env.SERVER_PORT || 5000)

// http
//     .createServer(postgraphileServer)
//     .listen(process.env.PORT || 5000);