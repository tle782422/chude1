import pgPromise from 'pg-promise';
const pg = pgPromise({});
export const db = pg("postgres://postgres:123456789@localhost:5432/webstory");
