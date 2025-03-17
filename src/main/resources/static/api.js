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
    },

    // Fetch DELETE /api/hero
    fetchDeleteAllHeroes: async () => {
        try {
            const response = await fetch(api.host + '/api/hero', {
                method: 'DELETE', // Especifica que é um método DELETE
                headers: {
                    'Content-Type': 'application/json',
                },
            });

            if (response.ok) {
                // Se a resposta for bem-sucedida
                console.log("Todos os heróis foram excluídos com sucesso.");
                return true;
            } else {
                // Se houver um erro na resposta
                console.error("Erro ao excluir todos os heróis.");
                return false;
            }
        } catch (error) {
            console.error("Erro de conexão:", error);
            return false;
        }
    }

};