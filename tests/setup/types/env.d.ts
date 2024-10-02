export { };

declare global {
    namespace NodeJS {
        interface ProcessEnv {
            ENV: "dev" | "prod" | "QA"| "APITesting",
        }
    }
}
