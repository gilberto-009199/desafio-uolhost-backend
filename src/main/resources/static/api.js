var api = {
    host: '',

    // Fetch GET /api/hero/group
    fetchListGroup: async () => {
        try {
            const response = await fetch( api.host + '/api/hero/group');

            return await response.json();
        } catch (error) {
            console.error(error);
            return null;
        }
    },

    // Fetch GET /api/hero
    fetchListHero: async () => {
        try {
            const response = await fetch( api.host + '/api/hero');

            return await response.json();

        } catch (error) {
            console.error(error);
            return null;
        }
    },

    // Fetch POST /api/hero
    fetchSaveHero: async (data) => {
        try {
            const response = await fetch( api.host + '/api/hero', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            });

            return await response.json();

        } catch (error) {
            console.error(error);
            return null;
        }
    }
};